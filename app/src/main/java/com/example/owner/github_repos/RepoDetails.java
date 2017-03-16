package com.example.owner.github_repos;

/**
 * Created by Owner on 16-Mar-17.
 */

public class RepoDetails {


    private int id;
    private String name, username, avatarUrl, desc, ownerUrl, RepoURL;


    public RepoDetails(int id, String name, String username, String avatarUrl, String desc, String ownerUrl, String repoURL) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.desc = desc;
        this.ownerUrl = ownerUrl;
        RepoURL = repoURL;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public void setOwnerUrl(String ownerUrl) {
        this.ownerUrl = ownerUrl;
    }

    public void setRepoURL(String repoURL) {
        RepoURL = repoURL;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getOwnerUrl() {
        return ownerUrl;
    }

    public String getRepoURL() {
        return RepoURL;
    }


}
