import java.util.List;

/**
 * Created by Wouter on 5/3/2016.
 */
public class Station {
    long id;
    String name;
    float lat;
    float lon;
    List<Double> velocities;

    public Station(long id, String name, float lat, float lon, List<Double> velocities) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.velocities = velocities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public List<Double> getVelocities() {
        return velocities;
    }

    public void setVelocities(List<Double> velocities) {
        this.velocities = velocities;
    }
    public void addVelocity(Integer epoch,Double velocity){
        this.velocities.add(epoch,velocity);
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", velocities=" + velocities +
                '}';
    }
}
