package Collection;

public class Book {
	private int BookId;
	private String Bookname;
	
	public Book(int BookId, String Bookname) {
		this.BookId = BookId;
		this.Bookname = Bookname;
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getBookname() {
		return Bookname;
	}

	public void setBookname(String bookname) {
		Bookname = bookname;
	}
	
	@Override
	public String toString() {
		return Bookname + "도서의 아이디는 " + BookId + "입니다.";
	}
}
