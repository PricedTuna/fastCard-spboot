package com.example.fastCard.mappers;

import com.example.fastCard.dtos.UserResponseDto;
import com.example.fastCard.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseMapper {

    public static List<UserResponseDto> toUserResponseDtoList(List<User> users){
        if(users == null){
            return null;
        }

        return users.stream().map(UserResponseMapper::toDto).collect(Collectors.toList());
    }


    public static UserResponseDto toDto(User user){
        if(user == null){
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setDecks( DeckResponseMapper.toDeckDtoList(user.getDecks()));

        return userResponseDto;
    }

}
