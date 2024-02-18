package com.moviereview.web.dto;

import com.moviereview.web.models.Movie;
import com.moviereview.web.models.UserEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    @NotEmpty(message = "Title is required")
    private String title;
    @Min(value = 1, message = "Score should be between 1 and 10")
    @Max(value = 10, message = "Score should be between 1 and 10")
    private int score;
    @NotEmpty(message = "Description is required")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private UserEntity createdBy;
    private Movie movie;
}
