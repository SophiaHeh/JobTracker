package com.tnite.jobwinner.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "coding_problems")
public class CodingProblem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id = UUID.randomUUID();; // Unique identifier

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private SWE job;


    @Column(name = "question", nullable = false, length = 2000)
    private String question;

    @Column(name = "platform", nullable = false, length = 100)
    private String platform; // LeetCode, HackerRank, etc.

    @Column(name = "difficulty", nullable = false, length = 50)
    private String difficulty; // Easy, Medium, Hard

    public CodingProblem() {
    }

    public CodingProblem(SWE job, String question, String platform,
        String difficulty) {
        this.job = job;
        this.question = question;
        this.platform = platform;
        this.difficulty = difficulty;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GeneralJob getJob() {
        return this.job;
    }

    public void setJob(SWE job) {
        this.job = job;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodingProblem that = (CodingProblem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
