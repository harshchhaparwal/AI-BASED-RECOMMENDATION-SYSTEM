import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.File;
import java.util.List;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // Load sample data (userId, itemId, preference)
            DataModel model = new FileDataModel(new File("data.csv"));
            
            // Define similarity metric (Pearson correlation)
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            
            // Define neighborhood size (nearest N users)
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
            
            // Create recommender system
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            
            // Generate recommendations for user ID 1
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);
            
            // Display recommendations
            System.out.println("Recommended items for user 1:");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item: " + recommendation.getItemID() + " | Score: " + recommendation.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
