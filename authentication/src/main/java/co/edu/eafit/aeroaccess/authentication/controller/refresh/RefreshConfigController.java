package co.edu.eafit.aeroaccess.authentication.controller.refresh;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Setter
@RefreshScope
@RequestMapping("api/refresh")
public class RefreshConfigController {
    @Value("${app.properties}")
    private String properties;

    @GetMapping("properties")
    public String getproperties() {
        return this.properties;
    }
}
