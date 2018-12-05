package kz.enu.lions.telegrambot;

public class Answer {
    public String name;
    public boolean right;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Answer{" +
                ", name='" + name + '\'' +
                ", right=" + right +
                '}';
    }
}
