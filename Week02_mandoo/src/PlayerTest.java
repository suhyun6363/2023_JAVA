interface Controllable {
	void play();
	void stop();
}

class VideoPlayer implements Controllable{
	public void play() {
		System.out.println("비디오 재생 중");
	}
	public void stop() {
		System.out.println("비디오 재생 중지");
	}
}

public class PlayerTest {
	public static void main(String args[]) {
		VideoPlayer c = new VideoPlayer();
		c.play();
		c.stop();
	}
}
