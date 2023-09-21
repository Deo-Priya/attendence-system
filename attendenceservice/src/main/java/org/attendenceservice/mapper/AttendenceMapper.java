package org.attendenceservice.mapper;

import org.attendenceservice.dto.AttendenceDto;
import org.attendenceservice.entity.Attendence;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttendenceMapper {
	
	public AttendenceDto attendenceToAttendenceDto(Attendence attendence);

}
