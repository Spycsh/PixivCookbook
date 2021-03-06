package PixivCookbook.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PixivCookbook.Model.Ingredient;
import PixivCookbook.Model.Recipe;
import PixivCookbook.Model.Step;
import PixivCookbook.Controller.DBController;

/**
 * Before run this test please make sure you
 * have deleted the records of your database
 * and initialize it with CookBookApp
 * @author Spycsh

 *
 */
@FixMethodOrder(MethodSorters.JVM)  // run the test function with given order
public class JunitDBController {
	DBController aSql_test;
	Recipe aRecipe;
	Ingredient aIngredient;

	Recipe testRecipe ;


	@BeforeEach
	public void setUp() throws Exception {
		aSql_test = DBController.getInstance();
		aSql_test.run();
		testRecipe = new Recipe("testRecipe","Shanghai dish" , 4);
		testRecipe.restoreImg();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() throws SQLException {
		assertTrue(!aSql_test.getConnect().isClosed());
	}

	@Test
	public void testClose() throws SQLException {
		aSql_test.getConnect().close();
		assertTrue(aSql_test.getConnect().isClosed());
	}


	/**
	 * test search by approximate name
	 */
	@Test
	public void testSearchAllMatchedRecipes() {
		List<Recipe> atestRecipeList = aSql_test.searchAllMatchedRecipes("ong");
		List<Recipe> aRecipeList = new LinkedList<Recipe>() {{
			add(aSql_test.getRecipeBySearchfromDatabase(1));  // Gong Bao Jiding
			add(aSql_test.getRecipeBySearchfromDatabase(2));  // Hong Shao Rou
		}};
		assertEquals(atestRecipeList.toString(), aRecipeList.toString());  // should not compare the object directly

	}

	/**
	 * test getRecipeBySearchfromDatabase function first
	 * if you set the recipe id to be 999, then the new one id will be 999+1
	 */
	@Test
	public void testAddRecipetoDatabase() {
		aSql_test.addRecipetoDatabase(testRecipe, 665);  // will be 666
		aRecipe = aSql_test.getRecipeBySearchfromDatabase(665);
		assertEquals(aRecipe.getRecipeName(), "testRecipe");
		assertEquals(aRecipe.getCuisineName(), "Shanghai dish");
	}

	/**
	 * test the function of getting
	 * the whole number of the recipes
	 */
	@Test
	public void testAssignID() {
		int aID = aSql_test.assignID();
		assertEquals(aID, aSql_test.getAllRecipeNamesfromDatabase().size()+1);
	}



	/**test update the name function
	 * @throws InterruptedException
	 *
	 */
	@Test
	public void testSaveRecipName() throws InterruptedException {
		aSql_test.saveRecipName(1, "testRecipe_modified");
		aRecipe = aSql_test.getRecipeBySearchfromDatabase(1);
		assertEquals(aRecipe.getRecipeName(), "testRecipe_modified");
		aSql_test.saveRecipName(1, "Gong Bao Jiding");
		aRecipe = aSql_test.getRecipeBySearchfromDatabase(1);
		assertEquals(aRecipe.getRecipeName(), "Gong Bao Jiding");
	}


	/**
	 * test the update description function
	 */
	@Test
	public void testSaveDescription() {
		aSql_test.saveDescription(1, "aaaaa");
		aRecipe = aSql_test.getRecipeBySearchfromDatabase(1);
		assertEquals(aRecipe.getCuisineName(), "aaaaa");
	}



	/**
	 * test the save image path function
	 */
	@Test
	public void testSaveImagePath() {
		aSql_test.saveImagePath(1, "change.jpg");
		aRecipe = aSql_test.getRecipeBySearchfromDatabase(1);
		assertEquals(aRecipe.getImgAddress(), "img\\1haha.jpg");  // cache picture
	}

	/**
	 * test the get all recipe names from database
	 */
	@Test
	public void testGetAllRecipeNamesfromDatabase() {
		List<String> atestList= new LinkedList<String>() {{
			add("Gong Bao Jiding");
			add("Hong Shao Rou");
			add("Suan La Fen");

		}};

		List<String> aList = aSql_test.getAllRecipeNamesfromDatabase();
		assertEquals(atestList, aList);

	}

	/**
	 * test
	 */
	@Test
	public void testGetRecipeBySearchfromDatabase() {
		aRecipe = aSql_test.getRecipeBySearchfromDatabase(2);
		assertEquals(aRecipe.getRecipeName(), "Suan La Fen");
	}

	/**
	 * test get ingredient list from database
	 */
	@Test
	public void testGetIngredientsfromDatabase() {
		List<Ingredient> aIngredientList = aSql_test.getIngredientsfromDatabase(1);
		assertEquals(aIngredientList.get(0).getName(),"Chiangang vinegar");
	}

	/**
	 * test get step list from database
	 */
	@Test
	public void testGetStepsfromDatabase() {
		List<Step> aStepList = aSql_test.getStepsfromDatabase(1);  // recipe 1's step
		assertEquals(aStepList.get(3).getContent(),"mix with vinegar, sesame oil, and dark soy sauce." );
	}



	/**
	 * test search all the ID of a recipe by
	 * giving the approximate name
	 * e.g. search "ong"
	 * return the id of "Gong Bao Jiding" and
	 * "Hong Shao rou"
	 */
	@Test
	public void testSearchAllMatchedID() {
		List<Integer> aList = aSql_test.searchAllMatchedID("ong");
		List<Integer> atestList = new LinkedList<Integer>() {{
			add(1);
			add(2);
		}};
		assertEquals(aList, atestList);
	}

	// Random get, cannot test
//	@Test
//	public void testGetRecipesForMainPage() {
//		List<Recipe> aList= aSql_test.getRecipesForMainPage();   // list desc, so first element with index 0 is testRecipe!
//		assertEquals(aList.get(0).getRecipeName(),"testRecipe");
//
//	}

	/**
	 * test add forbidden pair
	 */
	@Test
	public void testAddForbiddenPair() {
		aSql_test.addForbiddenPair("f_2", "f_1");
		LinkedList<String> aList = new LinkedList<String>();
		aList.add("f_2");
		assertEquals(aSql_test.getForbiddenPair("f_1"), aList);
	}

	/**
	 * test add forbidden pair to database
	 */
	@Test
	public void testGetForbiddenPair() {
		LinkedList<String> alist = new LinkedList<String>();
		alist.add("persimmon");
		assertEquals(aSql_test.getForbiddenPair("milk"),alist);
	}


	/**
	 * test the delete function
	 */
	@Test
	void testDeleteRecipefromDatabase() {
		List<String> aList = aSql_test.getAllRecipeNamesfromDatabase();
		aSql_test.deleteRecipefromDatabase(1);
		List<String> aList2 = aSql_test.getAllRecipeNamesfromDatabase();
		assertNotEquals(aList, aList2);
	}

}
