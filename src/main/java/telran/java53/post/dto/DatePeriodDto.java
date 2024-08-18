package telran.java53.post.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class DatePeriodDto {
	LocalDateTime dateFrom;
	LocalDateTime dateTo;
}
