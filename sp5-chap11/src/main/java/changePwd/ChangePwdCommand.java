package changePwd;

public class ChangePwdCommand { //비밀번호변경폼에서 입력한 값을 전달받기 위한 클래스
	private String currentPassword; //기존 비밀번호
	private String newPassword; // 새 비밀번호
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
