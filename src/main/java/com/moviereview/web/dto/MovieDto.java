package com.moviereview.web.dto;

import com.moviereview.web.models.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MovieDto {
    private Long id;
    @NotEmpty(message = "Movie title should not be empty")
    private String title;
    @NotEmpty(message = "Genre should not be empty")
    private String genre;
    @NotEmpty(message = "Photo link should not be empty")
    private String photoUrl;
    @Size(min=20, max=2000, message = "The description should be between 20 and 2000 characters")
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private UserEntity createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<ReviewDto> reviews;
}
