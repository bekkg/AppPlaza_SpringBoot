package peaksoft.appplaza_springboot.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza_springboot.model.Application;
import peaksoft.appplaza_springboot.model.dto.ApplicationRequest;
import peaksoft.appplaza_springboot.model.dto.ApplicationResponse;

import java.time.LocalDate;

@Component
public class ApplicationMapper {
    public Application mapToEntity(ApplicationRequest request){
        Application application = new Application();
        application.setName(request.getName());
        application.setDescription(request.getDescription());
        application.setDeveloper(request.getDeveloper());
        application.setVersion(request.getVersion());
        application.setCreateDate(LocalDate.now());

        return application;
    }
    public ApplicationResponse mapToResponse(Application application){
        return ApplicationResponse
                .builder()
                .id(application.getId())
                .name(application.getName())
                .description(application.getDescription())
                .developer(application.getDeveloper())
                .version(application.getVersion())
                .createDate(application.getCreateDate())
                .appStatus(application.getAppStatus())
                .genreName(application.getGenreName())
                .build();
    }
    public ApplicationResponse mapToApplicationResponse(Application application){
        return ApplicationResponse
                .builder()
                .id(application.getId())
                .name(application.getName())
                .description(application.getDescription())
                .developer(application.getDeveloper())
                .version(application.getVersion())
                .createDate(application.getCreateDate())
                .appStatus(application.getAppStatus())
                .genreName(application.getGenreName())
                .build();
    }
}
