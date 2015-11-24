package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
@Named("cabinetBean")
@ConversationScoped
public class CabinetBean implements Serializable{
	private int slotNumber;
	private List<TxBoardBean> txBoards;

	public CabinetBean() {
		this(0, new ArrayList<TxBoardBean>());
	}

	public CabinetBean(int slot, List<TxBoardBean> tx) {
		this.slotNumber = slot;
		this.txBoards = tx;
		
		ensureTXBoardOrder();
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public List<TxBoardBean> getTxBoards() {
		return txBoards;
	}

	public void addTxBoard(TxBoardBean t) {
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
