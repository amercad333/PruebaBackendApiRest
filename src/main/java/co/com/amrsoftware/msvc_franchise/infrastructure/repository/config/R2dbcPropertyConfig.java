package co.com.amrsoftware.msvc_franchise.infrastructure.repository.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class R2dbcPropertyConfig {
    private String database;
    private String hostName;
    private String password;
    private Integer port;
    private String username;
}
