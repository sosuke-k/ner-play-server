package controllers;

import java.net.*;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.lang.ClassNotFoundException;

import java.util.ArrayList;
import java.util.List;

import play.mvc.*;
import play.libs.Json;

import views.html.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Doc;
import models.Token;
import models.Tokens;
import models.Status;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.Triple;
import edu.stanford.nlp.ling.CoreAnnotations.*;


public class NERController extends Controller {

    private static String current = "";
    private static AbstractSequenceClassifier<CoreLabel> classifier = null;

    private boolean initialize(String classifier) {
      try {
        URL url = new URL("http://" + this.request().host() + "/assets/classifiers/" + classifier);
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("Accept-Encoding", "gzip");
        GZIPInputStream gzin = new GZIPInputStream(connect.getInputStream());

        this.classifier = CRFClassifier.getClassifier(gzin);

        gzin.close();
        connect.disconnect();

        return true;
      } catch ( IOException e) {
        System.out.println(e.getMessage());
      } catch ( ClassNotFoundException e) {
        System.out.println(e.getMessage());
      }
      return false;
    }

    private Tokens tag_sent(String text) {
      Tokens tokens = new Tokens();
      for (List<CoreLabel> lcl : this.classifier.classify(text)) {
        for (CoreLabel cl : lcl) {
          tokens.add(new Token(cl.originalText(), cl.get(AnswerAnnotation.class)));
        }
      }
      return tokens;
    }

    public Result index(String classifier) {
      // 必ず一度 http://localhost:9000/ner に GET リクエストした後でないと NER できない仕様
      if (this.classifier == null || this.current != classifier) {
        boolean success = this.initialize(classifier);
        if (!success) {
          return ok(Json.toJson(new Status(false, this.current)));
        }
        this.current = classifier;
      }
      return ok(Json.toJson(new Status(true, this.current)));
    }

    public Result tag() {
      if (this.classifier == null) {
        return ok(Json.toJson(new Status(false, this.current)));
      }

      JsonNode json = request().body().asJson();
      // String text = json.findPath("text").getTextValue();
      String jsonstring = Json.stringify(json);

      ObjectMapper mapper = new ObjectMapper();
      Doc doc = null;
      try {
        doc  = mapper.readValue(jsonstring, Doc.class);
      } catch ( IOException e) {
        System.out.println( "IOException: could not mapper.readValue" );
      }

      return ok(Json.toJson(this.tag_sent(doc.text)));
    }

    public Result example() {
      if (this.classifier == null) {
        return ok(Json.toJson(new Status(false, this.current)));
      }

      String[] example = {"Good afternoon Rajat Raina, how are you today?",
                          "I go to school at Stanford University, which is located in California." };

      Tokens tokens = new Tokens();

      int i=0;
      for (String str : example) {
        for (List<CoreLabel> lcl : this.classifier.classify(str)) {
          for (CoreLabel cl : lcl) {
            System.out.print(i++ + ": ");
            System.out.println(cl.toShorterString());
            tokens.add(new Token(cl.originalText(), cl.get(AnswerAnnotation.class)));
          }
        }
      }

      return ok(Json.toJson(tokens));
    }

}
