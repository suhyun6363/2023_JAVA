package Week04_01;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Person {
	String name;
	String tel;
	String gender;
	String address;
	
	public Person(String name, String tel, String gender, String address) {
		super();
		this.name = name;
		this.tel = tel;
		this.gender = gender;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}

public class TelNumber extends JFrame{
	ArrayList<Person> list = new ArrayList<>();
	private JPanel contentPane;
	private JTextField nmTextField, telTextField;
	private JTextArea addrTextArea;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelNumber frame = new TelNumber();
					frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelNumber() {
		setTitle("Address Booke");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(12, 10, 57, 15);
		contentPane.add(nameLabel);
		
		JLabel telLabel = new JLabel("전화번호");
		telLabel.setBounds(12, 42, 57, 15);
		contentPane.add(telLabel);
		
		nmTextField = new JTextField();
		nmTextField.setBounds(81, 7, 243, 21);
		contentPane.add(nmTextField);
		nmTextField.setColumns(10);
		
		telTextField = new JTextField();
		telTextField.setColumns(10);
		telTextField.setBounds(81, 39, 243, 21);
		contentPane.add(telTextField);
		
		// 성별 추가
		JPanel genderPanel = new JPanel();
		JLabel genderLabel = new JLabel("성별");
		genderLabel.setBounds(12, 79, 57, 15);
		contentPane.add(genderLabel);
		
		JRadioButton man = new JRadioButton("남성");
		JRadioButton woman = new JRadioButton("여성");
		
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(man);
		genderGroup.add(woman);
		genderPanel.add(man);
		genderPanel.add(woman);
		genderPanel.setBounds(20, 70, 243, 25);
		contentPane.add(genderPanel);

		JLabel addrLabel = new JLabel("주소");
		addrLabel.setBounds(12, 111, 57, 15);
		contentPane.add(addrLabel);
		
		addrTextArea = new JTextArea();
		addrTextArea.setBounds(12, 142, 312, 67);
		contentPane.add(addrTextArea);
		
		JButton saveBtn = new JButton("저장");
		saveBtn.setBounds(12, 230, 97, 23);
		contentPane.add(saveBtn);
		saveBtn.addActionListener(e -> {
			String name = nmTextField.getText();
			String tel = telTextField.getText();
			
			String sex;
			if(genderGroup.getSelection() == man)
				sex = "남성";
			else 
				sex = "여성";
			
			String address = addrTextArea.getText();
			list.add(new Person(name, tel, sex, address));
		});
		
		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(117, 230, 97, 23);
		contentPane.add(searchBtn);
		searchBtn.addActionListener(e -> {
			String name = nmTextField.getText();
            String gender = man.isSelected() ? "남성" : "여성";

			Person contact = searchContact(name, gender);
			if(contact != null) {
				telTextField.setText(contact.getTel());
				addrTextArea.setText(contact.getAddress());
			}
			else {
				telTextField.setText("");
				addrTextArea.setText("");
			}			
		});
		
		JButton exitBtn = new JButton("종료");
		exitBtn.setBounds(227, 230, 97, 23);
		contentPane.add(exitBtn);
		exitBtn.addActionListener(e -> {
			System.exit(0);
		});
	}
	
	public Person searchContact(String name, String gender) {
        for (Person p : list) {
            if (p.getName().equals(name) && p.getGender().equals(gender)) {
                return p;
            }
        }
        return null; // 해당하는 연락처를 찾지 못한 경우
    }
}
