package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "C2BToken")
public class C2BToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Token_id")
    private int tokenId;

    @Basic
    @Column(name = "token")
    private String token;

    @Basic
    @Column(name = "expirytime")
    private LocalDateTime expirytime;

    @Basic
    @Column(name = "tokenStatus")
    private boolean tokenStatus;

    public C2BToken() {
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirytime() {
        return expirytime;
    }

    public void setExpirytime(LocalDateTime expirytime) {
        this.expirytime = expirytime;
    }

    public boolean isTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(boolean tokenStatus) {
        this.tokenStatus = tokenStatus;
    }


}
