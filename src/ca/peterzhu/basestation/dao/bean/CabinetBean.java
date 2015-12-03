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
 * Contains all the the fields to represent a Cabinet. This is a managed bean
 * using CDI to interact with the HTML JSF pages.
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

	/**
	 * Initializes all the fields with their empty (non null) values.
	 */
	public CabinetBean() {
		this(0, new LinkedList<TxBoardBean>());
	}

	/**
	 * Initializes all the fields with the specified parameters.
	 * 
	 * @param slot
	 *            the slot number of this cabinet
	 * @param tx
	 *            the list of TX boards
	 */
	public CabinetBean(int slot, List<TxBoardBean> tx) {
		this.slotNumber = slot;
		this.txBoards = tx;

		ensureTXBoardOrder();
	}

	/**
	 * Initializes all the fields with a copy of the CabinetBean passed in.
	 * 
	 * @param c
	 *            the CabinetBean to be copied
	 */
	public CabinetBean(CabinetBean c) {
		this(c.getSlotNumber(), new LinkedList<TxBoardBean>());

		for (TxBoardBean txb : c.getTxBoards()) {
			txBoards.add(new TxBoardBean(txb));
		}

		ensureTXBoardOrder();
	}

	/**
	 * Overrides the equals method in the Object class. Compares the list of TX
	 * Boards and the slot number of this CabinetBean and the CabinetBean passed
	 * in.
	 * 
	 * @param o
	 *            the Object to compare this CabinetBean tos
	 * @return true if this CabinetBean is the same as the one passed in; false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof CabinetBean) {
			CabinetBean c = (CabinetBean) o;

			// THIS CODE NOT TESTED!!!!
			if (c.getSlotNumber() != this.getSlotNumber())
				return false;

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

	/**
	 * Copies the CabinetBean passed in into this CabinetBean.
	 * 
	 * @param c
	 *            the CabinetBean to be copied
	 * @param redirect
	 *            the redirect URL to return
	 * @return the redirect URL
	 */
	public String setThisObject(CabinetBean c, String redirect) {
		this.slotNumber = c.getSlotNumber();

		this.txBoards = new LinkedList<TxBoardBean>();

		for (TxBoardBean tx : c.txBoards) {
			this.txBoards.add(new TxBoardBean(tx));
		}

		return redirect;
	}

	/**
	 * @return the slot number
	 */
	public int getSlotNumber() {
		return slotNumber;
	}

	/**
	 * @param slotNumber
	 *            the slot number to set
	 */
	public void setSlotNumber(int slotNumber) {
		System.out.println("Set slot number from " + this.slotNumber + " to " + slotNumber);

		this.slotNumber = slotNumber;
	}

	/**
	 * @return the list of TxBoardBean
	 */
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

	/**
	 * Adds the TxBoardBean passed in to the end of the list of TxBoardBean.
	 * 
	 * @param t
	 *            the TxBoardBean to add to the list
	 * @param redirect
	 *            The redirect URL to return
	 * @return the redirect URL
	 */
	public String addTxBoard(TxBoardBean t, String redirect) {
		t.setSlotNumber(txBoards.size());
		txBoards.add(t);

		ensureTXBoardOrder();

		return redirect;
	}

	/**
	 * Removes the first occurrence of the TxBoardBean (found using the equals
	 * method in TxBoardBean) from the list of TxBoardBean.
	 * 
	 * @param t
	 *            the TxBoardBean to remove
	 */
	public void removeTxBoard(TxBoardBean t) {
		txBoards.remove(t);

		ensureTXBoardOrder();
	}

	/**
	 * Return the TxBoardBean in position slot number - 1 from the list of
	 * TxBoardBean.
	 * 
	 * @param slotNumber
	 *            the slot number of the TxBoardBean
	 * @return the TxBoardBean at position slot number - 1
	 */
	public TxBoardBean getTxBoard(int slotNumber) {
		return txBoards.get(slotNumber - 1);
	}

	/**
	 * Loops through the list of TxBoardBean and ensures all the slot numbers
	 * are in order. If it is not in order, then the slot number is set to the
	 * correct value.
	 */
	private void ensureTXBoardOrder() {
		for (int i = 0; i < txBoards.size(); i++) {
			if (txBoards.get(i).getSlotNumber() != (i + 1)) {
				txBoards.get(i).setSlotNumber(i + 1);
			}
		}
	}

	/**
	 * Discards the creation of this bean by clearing all the fields.
	 * 
	 * @param redirect
	 *            the redirect URL to return
	 * @return the redirect URL
	 */
	public String discard(String redirect) {
		clearFields();

		return redirect;
	}

	/**
	 * Clears all the fields to their empty (non null) values.
	 */
	public void clearFields() {
		slotNumber = 0;
		txBoards = new LinkedList<>();
		baseStationPage = "";
	}

}
