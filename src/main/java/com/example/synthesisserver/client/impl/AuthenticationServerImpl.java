package com.example.synthesisserver.client.impl;

import com.example.synthesisserver.communicate.MSCommunicateHelper;
import com.example.synthesisserver.dto.UserDTO;
import com.example.synthesisserver.dto.UserDTOList;
import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthenticationServerImpl implements AuthenticationServer {
    @Override
    public Map<Long, UserDTO> getUserMap() {
        UserDTOList userDTOList = MSCommunicateHelper.httpGetMethodExecutive(
                "http://localhost:8082/api/v1/user/list",
                UserDTOList.class
        );
        return userDTOList.getUserDTOs().stream().collect(Collectors.toMap(UserDTO::getId, Function.identity()));
    }
}
