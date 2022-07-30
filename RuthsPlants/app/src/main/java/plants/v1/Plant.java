package plants.v1;

import java.util.Date;

public class Plant {

        private String mPlantName;
        private String mPlantPictureUrl;
        private com.google.firebase.Timestamp mPlantDate;

        public Plant(){}

        public Plant(String pName, String pUrl, com.google.firebase.Timestamp pDate){

            this.mPlantName = pName;
            this.mPlantPictureUrl = pUrl;
            this.mPlantDate = pDate;

        }


        public String getRecipeName(){
            return mPlantName;
        }

        public String getPictureUrl(){
            return mPlantPictureUrl;
        }

        public com.google.firebase.Timestamp getTimestamp(){
            return mPlantDate;
        }


}
