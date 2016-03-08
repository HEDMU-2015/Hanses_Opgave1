package opgave1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Store {
	private int maxHeight;
	private boolean receiveFirst;
	private List<Place> store = new ArrayList<>();
	private int maxContainers = 0;
	private int maxPlaces = 0;
	
	public Store(int maxHeight, boolean receiveFirst) {
		this.maxHeight = maxHeight;
		this.receiveFirst = receiveFirst;
	}
	
	public void store(Container cont) {
		if (!receiveFirst) {
			remove(cont.getDateOfArrival());
		}
		receive(cont);
		int actualC = getActualContainers();
		if (maxContainers < actualC) {
			maxContainers = actualC;
		}
		int actualP = getActualPlaces();
		if (maxPlaces < actualP) {
			maxPlaces = actualP;
		}
		if (receiveFirst) {
			remove(cont.getDateOfArrival());
		}
	}
	
	private void remove(LocalDate currentDate) {
		for (int i = store.size()-1; i > -1; i--) {
			Place space = store.get(i);
			while(space.size() > 0 && !space.pickupDate().isAfter(currentDate)) {
				space.remove();
			}
			if (space.size() == 0) {
				store.remove(space);
			}
		}
	}
	
	private void receive(Container cont) {
		boolean placed = false;
		for (Place place : store) {
			if (!placed && place.size() < maxHeight && !place.pickupDate().isBefore(cont.getPickupDate())) {
				place.add(cont);
				placed = true;
				break;
			}
		}
		if (!placed) {
			store.add(new Place(cont));
		}
		
	}
	
//	private void receive(Container cont) {
//		Place best = null;
//		for (Place place : store) {
//			if (place.size() < maxHeight && !place.pickupDate().isBefore(cont.getPickupDate())) {
//				if (best == null) {
//					best = place;
//				} else if (place.pickupDate().isBefore(best.pickupDate())) {
//					best = place;
//				}
//			}
//		}
//		if (best != null) {
//			best.add(cont);
//		} else {
//			store.add(new Place(cont));
//		}
//		
//	}
	
	private class Place {
		private List<Container> place = new ArrayList<>();
		private Place(Container cont) {
			place.add(cont);
		}
		
		int size() {
			return place.size();
		}
		
		LocalDate pickupDate() {
			return place.get(size()-1).getPickupDate();
		}
		
		void add(Container cont) {
			place.add(cont);
		}
		
		void remove() {
			place.remove(size()-1);
		}

		@Override
		public String toString() {
			return "Place [place=" + place + "]";
		}
	}

	public int getMaxContainers() {
		return maxContainers;
	}

	public int getMaxPlaces() {
		return maxPlaces;
	}

	public int getActualContainers() {
		return store.stream().mapToInt(s -> s.size()).sum();
	}

	public int getActualPlaces() {
		return store.size();
	}

	@Override
	public String toString() {
		return "Store [maxHeight=" + maxHeight + ", receiveFirst=" + receiveFirst 
				+ ", maxContainers=" + maxContainers + ", maxPlaces=" + maxPlaces + ", store=" + store + "]";
	}

}
