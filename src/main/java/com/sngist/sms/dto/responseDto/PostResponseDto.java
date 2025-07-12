package com.sngist.sms.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PostResponseDto {
    private int userId;
    private int id;
    private String title;
    private String body;
}
