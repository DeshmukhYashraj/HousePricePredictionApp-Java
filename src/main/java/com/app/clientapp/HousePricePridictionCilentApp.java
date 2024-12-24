package com.app.clientapp;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.app.customException.DistrictNotFoundException;
import com.app.customException.StateNotFoundException;
import com.app.model.AminityModel;
import com.app.model.CityModel;
import com.app.model.DistrictModel;
import com.app.model.PropertyModel;
import com.app.model.StateModel;
import com.app.model.WardModel;
import com.app.service.AminityService;
import com.app.service.AminityServiceImpl;
import com.app.service.CityService;
import com.app.service.CityServiceImpl;
import com.app.service.DistrictService;
import com.app.service.DistrictServiceImpl;
import com.app.service.PropertyService;
import com.app.service.PropertyServiceImpl;
import com.app.service.StateService;
import com.app.service.StateServiceImpl;
import com.app.service.WardService;
import com.app.service.WardServiceImpl;

public class HousePricePridictionCilentApp {

	static int count = 0;
	static Scanner xyz = new Scanner(System.in);
	static StateService stateService = new StateServiceImpl();
	static DistrictService distService = new DistrictServiceImpl();
	static CityService cityService = new CityServiceImpl();
	static WardService wardService = new WardServiceImpl();
	static AminityService amiService = new AminityServiceImpl();
	static PropertyService propService = new PropertyServiceImpl();
	static int stateId;
	static int distId;
	static int cityId;
	static int wardId;
	static int pincode;
	static int aminityId;
//	static int propId;
	static String stateName;
	static String distName;
	static String cityName;
	static String wardName;
	static String aminityName;
//	static String propName;
	static List<WardModel> showWards;
	static List<AminityModel> amiList;

	public static void main(String[] args) throws Exception {

		System.out.println("<===== House Price Pridiction App =====>");

		do {
			System.out.println("\n-------------State-----------------");
			System.out.println("1: Add new state.");
			System.out.println("2: View all states.");
			System.out.println("3: Enter State name.");
			System.out.println("4: Delete state by id.");
			System.out.println("5: Update state by name.");

			System.out.println("\n-------------District--------------");
			System.out.println("6: Add new District by state.");
			System.out.println("7: Add districts in bulk.");
			System.out.println("8: View all districts by state.");
			System.out.println("9: Update district by name.");
			System.out.println("10: Delete district by name.");

			System.out.println("\n-------------City------------------");
			System.out.println("11: Add new City ");
			System.out.println("12: View all cities ");
			System.out.println("13: Update city name");
			System.out.println("14: Delete city name");

			System.out.println("\n-------------Ward------------------");
			System.out.println("15: Add new ward city wise");
			System.out.println("16: View all wards city wise");
			System.out.println("17: Update ward name");
			System.out.println("18: Delete ward.");

			System.out.println("\n-------------Aminity------------------");
			System.out.println("19: Add new Aminity");
			System.out.println("20: View all Aminities");
			System.out.println("21: Update Aminity name");
			System.out.println("22: Delete Aminity");
			
			System.out.println("\n-------------Property------------------");
			System.out.println("23: Add new Property");
			System.out.println("24: View all Properties");
			System.out.println("25: Update Property name");
			System.out.println("26	: Delete Property");


			System.out.println("\nEnter your choice:");

			int choice = xyz.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter state name:");
				xyz.nextLine();
				// String stateName = xyz.nextLine();
				// StateModel model = new StateModel(0, stateName);
				// boolean b = stateService.isAddNewState(new StateModel(0, stateName));
//				if (stateService.isAddNewState(new StateModel(0, stateName))) {
//					System.out.println("State added successfully......");
//				} else {
//					System.out.println("State not added.");
//				}				
//				String result = stateService.isAddNewState(new StateModel(0, stateName))? "State added successfully......":"State not added.";
//				System.out.println(stateService.isAddNewState(new StateModel(0, stateName))? "State added successfully......":"State not added.");
				System.out.println(
						stateService.isAddNewState(new StateModel(0, xyz.nextLine())) ? "State added successfully......"
								: "State not added.");
				break;

			case 2:
				Optional<List<StateModel>> o = stateService.getAllStates();

				/*if (list != null) {
					list.forEach((s) -> System.out.println(s.getStateId() + "\t" + s.getStateName()));
				} else {
					System.out.println("There is no data");
				}*/ /// or

				/*try {
					list.forEach((s) -> System.out.println(s.getStateId() + "\t" + s.getStateName()));
				} catch (NullPointerException npe) {
					System.out.println("Error is "+npe);
				}*/ /// or

				if (o.isPresent()) {
					List<StateModel> list = o.get();
					list.forEach((state) -> System.out.println(state.getStateId() + "\t" + state.getStateName()));
				} else {
					System.out.println("There is no data present is database");
				}

				break;

			case 3:
				System.out.println("Enter state name.");
				xyz.nextLine();
				stateName = xyz.nextLine();

				StateModel model = stateService.getStateByName(stateName);
				if (model != null) {
					System.out.println(model.getStateId() + "\t" + model.getStateName());
				} else {
					System.out.println("State not found");
				}
				break;

			case 4:
				System.out.println("Enter stateId to delete.");
				xyz.nextLine();
				int id = xyz.nextInt();

				StateModel deleteState = stateService.deleteStateById(id);
				if (deleteState != null) {
					System.out.println("State Deleted sussesfully........");
				} else {
					System.out.println("State not deleted.");
				}

				break;

			case 5:
				System.out.println("Enter current State name to update:");
				xyz.nextLine();
				String currStateName = xyz.nextLine();

				System.out.println("Entre new state name:");
				String newStateName = xyz.nextLine();

				boolean isUpdated = stateService.updateStateByName(currStateName, newStateName);
				System.out.println(isUpdated ? "State Updated succesfully..." : "State updated failed.");

				break;

			case 6:

				System.out.println("Enter State name: ");
				xyz.nextLine();
				String sName = xyz.nextLine();

				StateModel existingState = stateService.getStateByName(sName);
				if (existingState == null) {

					System.out.println("State not Found. Do you want to add this state?");
					System.out.println("1 : For add new state");
					System.out.println("2 : No, cancel");

					int userChoice = xyz.nextInt();
					xyz.nextLine();
					if (userChoice == 1) {
						boolean isAdded = stateService.isAddNewState(new StateModel(0, sName));
						System.out.println(isAdded ? "New State added successfully......" : "Failed to added.");
					} else {
						System.out.println("Operation canceled.");
					}

				} else {
					System.out.println("Enter District name: ");
					String dName = xyz.nextLine();

					boolean isDistrictAdded = distService.isAddNewDistByState(sName, dName);

					System.out.println(
							isDistrictAdded ? "District added sussesfully...." : "Some problem to add district.");
				}
				break;

			case 7:

				System.out.println("Enter State name: ");
				xyz.nextLine();
				String stateNameForDist = xyz.nextLine();
				boolean b = distService.isAddBulkDist(stateNameForDist);

				if (b) {
					System.out.println("All districts added Sussesfully.....");
				} else {
					System.out.println("There is some problem to add all districts.");
				}
				break;

			case 8:

				System.out.println("Enter state name: ");
				xyz.nextLine();
				String stName = xyz.nextLine();
				Optional<List<DistrictModel>> dl = distService.getAllDistrictByState(stName);

				if (dl.isPresent()) {
					List<DistrictModel> list = dl.get();
					list.forEach((dist) -> System.out.println(dist.getDistId() + "\t" + dist.getDistName()));
				} else {
					System.out.println("There is no district data present is database");
				}

				break;

			case 9:
				System.out.println("Enter state name: ");
				xyz.nextLine();
				String stateName2 = xyz.nextLine();

				System.out.println("Enter district name which want to update :");
				String currDistName = xyz.nextLine();

				System.out.println("Modify district name :");
				String newDistName = xyz.nextLine();

				boolean isDistUpdated = distService.updateDistNameByStateName(stateName2, currDistName, newDistName);
				System.out.println(isDistUpdated ? "District name updated sussesfully........."
						: "District name updation failed.");
				break;

			case 10:
				System.out.println("Enter state name: ");
				xyz.nextLine();
				String stateName3 = xyz.nextLine();

				StateModel existingStateName = stateService.getStateByName(stateName3);

				if (existingStateName != null) {
					System.out.println("Enter district name to delete:");
					String deleteDistName = xyz.nextLine();
					try {
						boolean isDistDeleted = distService.deleteDistNameByState(stateName3, deleteDistName);
						if (isDistDeleted) {
							System.out.println("District deleted sussesfully.......");
						} else {
							System.out.println("Failed to delete district.");
						}

					} catch (DistrictNotFoundException de) {
						System.out.println("Error " + de.getMessage());
					}
				} else {
					System.out.println("State not found.");
				}
				break;

			case 11:
				callStateDist();
				System.out.println("Enter city name: ");
				cityName = xyz.nextLine();
				CityModel cityModel = new CityModel();
				cityModel.setDistId(distId);
				cityModel.setStateId(stateId);
				cityModel.setCityName(cityName);
				b = cityService.isAddNewCity(cityModel);
				if (b) {
					System.out.println("City added sussesfully..............");
				} else {
					System.out.println("Failed to add city.");
				}
				break;

			case 12:
				callStateDist();
				List<CityModel> showAllCityByDist = cityService.showAllCityByDist(stateName, distName);
				System.out.println(stateName + "\t" + distName);
				if (showAllCityByDist.isEmpty()) {
					System.out.println("No records found for state: " + stateName + " and district: " + distName);
				} else {
					System.out.println("Records are :: ");
					showAllCityByDist.forEach(System.out::println);
				}
				break;

			case 13:
				callStateDistCity();
				System.out.println("Enter city Id which have to update: ");
				cityId = xyz.nextInt();
				xyz.nextLine();
				System.out.println("Enter new city name:");
				String newCityName = xyz.nextLine();
				boolean isCityUpdated = cityService.updateCityNameByDist(newCityName, cityId);
				System.out
						.println(isCityUpdated ? "City Name updated Sucessfully......" : "Failed to update city name.");
				break;

			case 14:
				callStateDistCity();
				System.out.println("Enter city name to delete: ");
				cityName = xyz.nextLine();
				boolean isCityDeleted = cityService.deleteCityByName(cityName);
				System.out.println(isCityDeleted ? "City deleted sussesfully......" : "Failed to delete city.");
				break;

			case 15:
				callStateDistCity();
				System.out.println("\nEnter city name: ");
				cityName = xyz.nextLine();
				System.out.println("Enter ward name to add: ");
				wardName = xyz.nextLine();
				System.out.println("Enter ward pincode: ");
				pincode = xyz.nextInt();

				b = wardService.isAddNewWard(cityName, wardName, pincode);
				if (b) {
					System.out.println("Ward added sussesfully.....");
				} else {
					System.out.println("Failed to add ward");
				}
				break;

			case 16:
				callStateDistCity();
				System.out.println("\nEnter city name: ");
				cityName = xyz.nextLine();

				showWards = wardService.showAllWardByCity(cityName);
				if (showWards.isEmpty()) {
					System.out.println("No wards found for city : " + cityName);
				} else {
					System.out.println("Wards is " + cityName + ":");
					showWards.forEach(ward -> System.out.println(ward.getWardId() + "\t" + ward.getWardName()));
				}
				break;

			case 17:
				callStateDistCityWard();
				System.out.println("\nEnter ward Id:");
				wardId = xyz.nextInt();
				xyz.nextLine();

				System.out.println("Enter new ward name:");
				String newWardName = xyz.nextLine();

				boolean isWardUpdated = wardService.updateWardName(newWardName, wardId);
				System.out.println(isWardUpdated ? "Ward name updated succesfully" : "Failed to update name.");
				break;

			case 18:
				callStateDistCityWard();
				System.out.println("\nEnter ward Id:");
				wardId = xyz.nextInt();
				xyz.nextLine();
				boolean isWardDeleted = wardService.deleteWard(wardId);
				System.out.println(isWardDeleted ? "Ward deleted succesfully...." : "Failed to deleted ward");
				break;


			case 19:
				System.out.println("\nEnter aminity name to add:");
				xyz.nextLine();
				aminityName = xyz.nextLine();

				AminityModel aminiModel = new AminityModel();
				aminiModel.setAminityName(aminityName);

				boolean isAmiAdded = amiService.isAddNewAminity(aminiModel);

				System.out.println(isAmiAdded ? "Aminity added successfullly" : "Failed to add aminity");
				break;

			case 20:
				List<AminityModel> amiList = amiService.getAllAminities();

				if (amiList.isEmpty()) {
					System.out.println("No any aminities found");
				} else {
					System.out.println("Available aminities:");
					System.out.println("=========================");
					amiList.forEach(amini -> System.out.println(amini.getAminityId() + "\t" + amini.getAminityName()));
				}
				break;
			case 21:
				callViewAminities();
				System.out.println("Enter aminty Id on which have to update: ");
				aminityId = xyz.nextInt();
				xyz.nextLine();
				System.out.println("Enter new name: ");
				aminityName = xyz.nextLine();
				boolean isAmiUpdated = amiService.updateAminityNameById(aminityName, aminityId);
				if (isAmiUpdated) {
					System.out.println("Aminity name updated successfully");
				} else {
					System.out.println("Failed to update name.");
				}
				break;

			case 22:
				callViewAminities();
				System.out.println("Enter aminity Id to delete: ");
				aminityId = xyz.nextInt();
				xyz.nextLine();
				boolean isAmiDeleted = amiService.deleteAminityName(aminityId);
				if (isAmiDeleted) {
					System.out.println("Aminity deleted succesfully......");
				} else {
					System.out.println("Failed to delete aminity");
				}
				break;
				
			case 23:
			    callStateDistCityWard();
			    System.out.println("Enter ward name: ");
			    wardName = xyz.nextLine(); // Reading the full ward name
			    wardId = wardService.getwardIdByName(wardName);

			    System.out.println("Enter Property details like \n1: Name, \n2: Address, \n3: Area in Square Feet, \n4: Price");

			    // Ensure correct handling of inputs
			    System.out.print("Property Name: ");
			    String propName = xyz.nextLine(); // Reading property name

			    System.out.print("Property Address: ");
			    String propAddress = xyz.nextLine(); // Reading property address

			    System.out.print("Area in Square Feet: ");
			    int sqFeetArea = xyz.nextInt(); // Reading area
			    xyz.nextLine(); // Consume the leftover newline

			    System.out.print("Property Price: ");
			    double propPrice = xyz.nextDouble(); // Reading price
			    xyz.nextLine(); // Consume the leftover newline

			    WardModel wardModel = new WardModel();
			    wardModel.setWardId(wardId);

			    System.out.println("Select Amenities for your property: ");
			    callViewAminities();

			    List<AminityModel> listAminities = new ArrayList<>();
			    while (true) {
			        System.out.println("Select Amenity Name, Id, and Price:");

			        System.out.print("Amenity Name: ");
			        String amName = xyz.nextLine(); // Reading amenity name

			        System.out.print("Amenity ID: ");
			        int amId = xyz.nextInt(); // Reading amenity ID
			        xyz.nextLine(); // Consume leftover newline

			        System.out.print("Amenity Price: ");
			        int amPrice = xyz.nextInt(); 
			        xyz.nextLine(); 

			        aminiModel = new AminityModel();  
			        aminiModel.setAminityId(amId);
			        aminiModel.setAminityName(amName);
			        aminiModel.setAmPrice(amPrice);

			        listAminities.add(aminiModel);

			        System.out.println("Do you want to add more amenities? (yes/no):");
			        String confirmMsg = xyz.nextLine().toLowerCase(); 
			        if (confirmMsg.equals("no")) {
			            break;
			        }
			    }

			    // PropertyModel constructor adjusted
			    PropertyModel propModel = new PropertyModel(wardModel,0, propName, propAddress, sqFeetArea, propPrice, listAminities);
			    boolean isAddNewProperty = propService.isAddNewProperty(propModel);

			    if (isAddNewProperty) {
			        System.out.println("Property added successfully!");
			    } else {
			        System.out.println("Failed to add property.");
			    }
			    break;

			case 24:
				callStateDistCityWard();
				System.out.println("Enter the ward where you want to addd the property");
				
				System.out.println();
				break;
			case 25:
				break;
			case 26:
				break;
			case 30:
				System.exit(0);
			default:
				System.out.println("Wrong choice");
				break;
			}
		} while (true);

	}

	////////////////
	public static void callStateDist() throws Exception {
		// Fetch all states
		Optional<List<StateModel>> os = stateService.getAllStates();
		if (os.isPresent()) {
			List<StateModel> stateList = os.get();

			// Display all available states
			System.out.println("Available States:");
			int count = 0; // Counter for states
			for (StateModel state : stateList) {
				System.out.println(++count + "\t" + state.getStateName());
			}

			// Input state name
			System.out.println("\nEnter state name:");
			xyz.nextLine(); // Consume leftover newline
			stateName = xyz.nextLine().trim().toLowerCase(); // Normalize input
			System.out.println("====================================");

			// Fetch districts for the entered state
			Optional<List<DistrictModel>> listDistName = distService.getAllDistrictByState(stateName);
			if (listDistName.isPresent()) {
				List<DistrictModel> districtList = listDistName.get();

				// Display available districts
				System.out.println("Available Districts:");
				for (DistrictModel dist : districtList) {
					System.out.println(dist.getDistId() + "\t" + dist.getDistName());
				}

				// Input district name
				System.out.println("\nEnter district name:");
				distName = xyz.nextLine().trim().toLowerCase(); // Normalize input

				// Filter and validate district name
				Optional<DistrictModel> selectedDistrict = districtList.stream()
						.filter(dist -> dist.getDistName().equalsIgnoreCase(distName)).findFirst();

				if (selectedDistrict.isPresent()) {
					System.out.println("====================================");
					stateId = stateService.getStateIdByName(stateName);
					distId = selectedDistrict.get().getDistId();
				} else {
					System.out.println("District '" + distName + "' not found in state: " + stateName);
				}
			} else {
				System.out.println("No districts found for the selected state: " + stateName);
			}
		} else {
			System.out.println("No states found in the database.");
		}
	}

	public static void callStateDistCity() throws Exception {
		callStateDist();
		// Fetch cities for the selected district
		List<CityModel> showAllCityByDist = cityService.showAllCityByDist(stateName, distName);

		if (showAllCityByDist.isEmpty()) {
			System.out.println("No cities found for state: " + stateName + " and district: " + distName);
		} else {
			System.out.println("Cities in " + distName + ":");
			showAllCityByDist.forEach(city -> System.out.println(city.getCityName()));
		}
	}

	public static void callStateDistCityWard() throws Exception {
		callStateDistCity();
		System.out.println("\nEnter city name: ");
		cityName = xyz.nextLine();

		System.out.println("=========================");
		showWards = wardService.showAllWardByCity(cityName);
		if (showWards.isEmpty()) {
			System.out.println("No wards found for city : " + cityName);
		} else {
			System.out.println("Wards is " + cityName + ":");
			showWards.forEach(ward -> System.out.println(ward.getWardId() + "\t" + ward.getWardName()));
		}
	}

	public static void callViewAminities() throws Exception {
		amiList = amiService.getAllAminities();

		if (amiList.isEmpty()) {
			System.out.println("No any aminities found");
		} else {
			System.out.println("Available aminities:");
			System.out.println("=========================");
			amiList.forEach(amini -> System.out.println(amini.getAminityId() + "\t" + amini.getAminityName()));
		}
	}
	
//	public static void callViewProperties() {
////		propList = propService.getAllProperties();
//		if (PropList.isEmpty) {
//			System.out.println("There is no any property available.");
//		} else {
//			System.out.println("Proprties available: ");
//			System.out.println("========================");
//			propList.forEach(prop -> System.out.println())
//		}
//	}

}
