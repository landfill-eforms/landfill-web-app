package com.landfill_eforms.server.entities.test;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="test.dbo.Sleep")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Sleep {

	@Id
	@Column(name="SleepPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="sleeps")
	//@JsonBackReference
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@JoinTable(name="test.dbo.SleepTest", joinColumns=@JoinColumn(name="SleepFK"), inverseJoinColumns=@JoinColumn(name="TestFK"))
	private Set<Test> tests;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Test> getTests() {
		return tests;
	}

	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}
	
	
}
