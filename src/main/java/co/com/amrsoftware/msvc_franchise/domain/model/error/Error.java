package co.com.amrsoftware.msvc_franchise.domain.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Error {
    private String error;
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
}
