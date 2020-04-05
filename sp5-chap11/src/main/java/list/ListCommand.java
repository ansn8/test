package list;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {
	//입력받는 값을 LocalDateTime의 형식에 맞게 변환하기 위해 @DateTimeFormat으로 변환
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime from;
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime to;

	public LocalDateTime getFrom() {
		return from;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
}
