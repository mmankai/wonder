import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;



public class Person {
	
	private Person _spouse;
	private NSMutableArray<Person> _children = new NSMutableArray<Person>();

	private String _name;
	private int _age;

	public Person(String name, int age) {
		_name = name;
		_age = age;
	}

	public Person() {

	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getAge() {
		return _age;
	}

	public void setAge(int age) {
		_age = age;
	}

	public Person getSpouse() {
		return _spouse;
	}

	public void setSpouse(Person spouse) {
		_spouse = spouse;
	}

	public NSArray<Person> getChildren() {
		return _children;
	}

	public void setChildren(NSArray<Person> children) {
		_children = children != null ? children.mutableClone() : null;
	}
}