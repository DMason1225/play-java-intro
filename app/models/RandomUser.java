package models;


public class RandomUser
{
    private ResultEntity[] results;

    private Info info;

    public ResultEntity[] getResults ()
    {
        return results;
    }

    public void setResults (ResultEntity[] results)
    {
        this.results = results;
    }

    public Info getInfo ()
    {
        return info;
    }

    public void setInfo (Info info)
    {
        this.info = info;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", info = "+info+"]";
    }
}
