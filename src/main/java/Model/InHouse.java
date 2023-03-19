package Model;

/**
 * Class that models an Inhouse Part
 * @author Winsly Cyrius
 */
public class InHouse extends Part{
        /**
         * The ID of the machine that manufactures the part.
         */
        private int machineId;

        /**
         * Constructs a new InHouse object with the given ID, name, price, stock, minimum and maximum levels, and machine ID.
         * @param id the unique identifier for the part
         * @param name the name of the part
         * @param price the price of the part
         * @param stock the current stock level of the part
         * @param min the minimum stock level required for the part
         * @param max the maximum stock level allowed for the part
         * @param machineId the ID of the machine that manufactures the part
         */
        public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
            super(id, name, price, stock, min, max);
            this.machineId = machineId;
        }

        /**
         * Returns the ID of the machine that manufactures the part.
         * @return the ID of the machine
         */
        public int getMachineId() {
            return machineId;
        }

        /**
         * Sets the ID of the machine that manufactures the part.
         * @param machineId the ID of the machine
         */
        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }
    }