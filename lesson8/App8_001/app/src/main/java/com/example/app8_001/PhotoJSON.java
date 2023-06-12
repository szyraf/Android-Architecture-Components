package com.example.app8_001;

public class PhotoJSON {
/*
[
  {
    "id": 168658294976855,
    "album": "koty",
    "originalName": "input.jpg",
    "url": "uploads\\koty\\upload_cfcd5fb1ce121e8b819ac1c3ab528e3b.jpg",
    "lastChange": "original",
    "history": [
      {
        "status": "original",
        "timestamp": "2023-06-12T15:15:49.768Z"
      }
    ],
    "tags": [
      {
        "newTag": "#fun",
        "popularity": 0
      },
      {
        "newTag": "#fun",
        "popularity": 1
      }
    ]
  }
]
*/

    private long id;
    private String album;
    private String originalName;
    private String url;
    private String lastChange;
    private History[] history;
    private Tags[] tags;

    public PhotoJSON(long id, String album, String originalName, String url, String lastChange, History[] history, Tags[] tags) {
        this.id = id;
        this.album = album;
        this.originalName = originalName;
        this.url = url;
        this.lastChange = lastChange;
        this.history = history;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public History[] getHistory() {
        return history;
    }

    public void setHistory(History[] history) {
        this.history = history;
    }

    public Tags[] getTags() {
        return tags;
    }

    public void setTags(Tags[] tags) {
        this.tags = tags;
    }
}

class History {
    private String status;
    private String timestamp;

    public History(String status, String timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

class Tags {
    private String newTag;
    private int popularity;

    public Tags(String newTag, int popularity) {
        this.newTag = newTag;
        this.popularity = popularity;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}