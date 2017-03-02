package models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LatinDemo
{
    public String  userId;
    public String id;
    public String title;
    public String body;

    public String getUserId()
    {
        return userId;
    }

    @JsonSetter("userId")
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getId()
    {
        return id;
    }

    @JsonSetter("id")
    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    @JsonSetter("title")
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBody()
    {
        return body;
    }

    @JsonSetter("body")
    public void setBody(String body)
    {
        this.body = body;
    }

    @JsonAnySetter()
    public void add(String key, String value)
    {
        System.out.println("value: " + key);
    }
}
