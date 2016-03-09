package com.dnake.test;

class Person {
	private Integer age;
	private String name;
	private String happy;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHappy() {
		return happy;
	}

	public void setHappy(String happy) {
		this.happy = happy;
	}

	@Override
	public String toString() {
		return "person [age=" + age + ", name=" + name + ", happy=" + happy
				+ "]";
	}


	@Override
	public boolean equals(Object obj) {
		Person p1=(Person)obj;
		if(this.age.equals(p1.getAge())){
			return true;
		}
		return false;
	}

	
}