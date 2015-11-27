package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
@Named("cabinetBean")
@ConversationScoped
public class CabinetBean implements Serializable {
	private int slotNumber;
	private List<TxBoardBean> txBoards;

	public CabinetBean() {
		this(0, new LinkedList<TxBoardBean>());
	}

	public CabinetBean(int slot, List<TxBoardBean> tx) {
		this.slotNumber = slot;
		this.txBoards = tx;

		ensureTXBoardOrder();
	}

	public CabinetBean(CabinetBean c) {
		this(c.getSlotNumber(), new LinkedList<TxBoardBean>());

		for (TxBoardBean txb : c.getTxBoards()) {
			txBoards.add(new TxBoardBean(txb));
		}

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

	public String addTxBoard(TxBoardBean t, String redirect) {
		// System.out.println("TX Board null: " + (t == null));
		// System.out.println("List null: " + (txBoards == null));

		t.setSlotNumber(txBoards.size());
		txBoards.add(t);

		ensureTXBoardOrder();

		return redirect;
	}

	private void ensureTXBoardOrder() {
		for (int i = 0; i < txBoards.size(); i++) {
			if (txBoards.get(i).getSlotNumber() != (i + 1)) {
				txBoards.get(i).setSlotNumber(i + 1);
			}
		}
	}

	public String discard(String redirect) {
		clearFields();

		return redirect;
	}

	public void clearFields() {
		slotNumber = 0;
		txBoards = new ArrayList<>();
	}

}
