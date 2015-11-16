package ca.peterzhu.basestation.dao.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class CabinetBean {
	private int slotNumber;
	private List<TXBoardBean> txBoards;

	public CabinetBean() {
		this (0, new ArrayList<TXBoardBean>());
	}

	public CabinetBean(int slot, List<TXBoardBean> tx) {
		this.slotNumber = slot;
		this.txBoards = tx;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public void addTXBoard(TXBoardBean t) {
		t.setSlotNumber(txBoards.size());
		txBoards.add(t);

		ensureTXBoardOrder();
	}

	private void ensureTXBoardOrder() {
		for (int i = 0; i < txBoards.size(); i++) {
			if (txBoards.get(i).getSlotNumber() != (i + 1)) {
				txBoards.get(i).setSlotNumber(i + 1);
			}
		}
	}

	private void clearFields() {
		slotNumber = 0;
		txBoards = new ArrayList<>();
	}

}
