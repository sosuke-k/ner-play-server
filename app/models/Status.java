package models;

public class Status {
  public String status;
  public String current;
  public String message;

  public Status(boolean status, String current) {
    if (status) {
      this.status = "available";
      this.message = "";
    } else {
      this.status = "unavailable";
      this.message = "you can use these classifiers: english.all.3class.distsim.crf.ser.gz, english.conll.4class.distsim.crf.ser.gz, english.muc.7class.distsim.crf.ser.gz";
    }
    this.current = current;
  }
}
