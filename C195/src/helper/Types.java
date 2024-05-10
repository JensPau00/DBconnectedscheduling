package helper;

public class Types {
    private String type;
    private int number;
    public Types(String type){
        this.type =type;
        number=1;
    }
    public void incNum(){
        number+=1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
