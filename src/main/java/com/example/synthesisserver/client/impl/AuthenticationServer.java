package com.example.synthesisserver.client.impl;

import com.example.synthesisserver.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface AuthenticationServer {
    Map<Long, UserDTO> getUserMap();
}
