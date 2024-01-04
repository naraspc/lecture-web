package com.example.thirdproject.mapper;

import com.example.thirdproject.dto.LectureServiceDto;
import com.example.thirdproject.entity.LectureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LectureMapper {

    @Mapping(source = "instructor.name", target = "instructorName")
    LectureServiceDto.LectureSaveResponse mapToSaveResponse(LectureEntity lectureEntity);

    LectureServiceDto.LectureUpdateResponse mapToUpdateResponse(LectureEntity lectureEntity);

}