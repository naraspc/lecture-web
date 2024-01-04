package com.example.thirdproject.mapper;

import com.example.thirdproject.dto.InstructorDto;
import com.example.thirdproject.dto.InstructorServiceDto;
import com.example.thirdproject.entity.InstructorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    @Mapping(target = "id", ignore = true) // id는 매핑에서 제외
    InstructorDto mapToInstructorDto(InstructorServiceDto.SaveInstructorResponse instructorServiceDto);
    InstructorServiceDto.SaveInstructorResponse mapToSaveInstructorResponse(InstructorEntity instructorEntity);
    InstructorServiceDto.SaveInstructorRequest mapToSaveControllerDto(InstructorDto.InstructorSaveRequest instructorSaveRequestDto);
    InstructorServiceDto.SaveInstructorRequest mapToUpdateControllerDto(InstructorDto.InstructorUpdateRequest instructorSaveRequestDto);

}


