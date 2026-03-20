package com.fitness.activityservice.service;

import com.fitness.activityservice.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service   // SP's service layer for business logic
@RequiredArgsConstructor  // lombok annotation for final variables
public class ActivityService {

    private final ActivityRepository activityRepository;   //ye required argument hai , to iska constructor generate hojayega(i.e RequiredArgsConstructor)
    //repository object hai jo database se baat karega
    private final UserValidationService userValidationService;

    public ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());

                if(!isValidUser){
                    throw new RuntimeException("Invalid user"+ request.getUserId());
                }

        Activity activity = Activity.builder()      //lombok build pattern for object creation.
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        
        return mapToResponse(savedActivity);  // mapToResponse jo hai wo 'savedActivity' ko 'ActivityResponse' me map karega
    }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
}
