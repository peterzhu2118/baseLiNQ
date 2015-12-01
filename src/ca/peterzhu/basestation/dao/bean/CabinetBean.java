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
	private String baseStationPage;

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

	@Override
	public boolean equals(Object o) {
		if (o instanceof CabinetBean) {
			CabinetBean c = (CabinetBean) o;

			if (c.getTxBoards().size() == this.getTxBoards().size()) {
				for (int i = 0; i < c.getTxBoards().size(); i++) {
					if (!c.getTxBoards().get(i).equals(this.getTxBoards().get(i)))
						return false;
				}

				return true;
			} else
				return false;
		} else
			return false;
	}

	public String setThisObject(CabinetBean c, String redirect) {
		this.slotNumber = c.getSlotNumber();

		this.txBoards = new LinkedList<TxBoardBean>();

		for (TxBoardBean tx : c.txBoards) {
			this.txBoards.add(new TxBoardBean(tx));
		}

		return redirect;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		System.out.println("Set slot number from " + this.slotNumber + " to " + slotNumber);

		this.slotNumber = slotNumber;
	}

	public List<TxBoardBean> getTxBoards() {
		return txBoards;
	}

	/**
	 * @return the baseStationPage
	 */
	public String getBaseStationPage() {
		return baseStationPage;
	}

	/**
	 * @param baseStationPage
	 *            the baseStationPage to set
	 */
	public void setBaseStationPage(String baseStationPage) {
		this.baseStationPage = baseStationPage;
	}

	public String addTxBoard(TxBoardBean t, String redirect) {
		t.setSlotNumber(txBoards.size());
		txBoards.add(t);

		ensureTXBoardOrder();

		return redirect;
	}

	public void removeTxBoard(TxBoardBean t) {
		txBoards.remove(t);

		ensureTXBoardOrder();
	}

	public TxBoardBean getTxBoard(int slotNumber) {
		return txBoards.get(slotNumber - 1);
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
		txBoards = new LinkedList<>();
		baseStationPage = "";
	}

}
