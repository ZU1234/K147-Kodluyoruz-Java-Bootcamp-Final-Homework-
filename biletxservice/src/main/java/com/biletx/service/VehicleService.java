package com.biletx.service;

import com.biletx.converter.BasketConverter;
import com.biletx.converter.VehicleConverter;
import com.biletx.enums.GenderType;
import com.biletx.enums.UserType;
import com.biletx.enums.VehicleStatus;
import com.biletx.enums.VehicleType;
import com.biletx.exception.TicketDoesNotException;
import com.biletx.exception.UserDoesNotException;
import com.biletx.exception.VehicleDoesNotException;
import com.biletx.model.Basket;
import com.biletx.model.User;
import com.biletx.model.Vehicle;
import com.biletx.repository.BasketRepository;
import com.biletx.repository.TicketRepository;
import com.biletx.repository.VehicleRepository;
import com.biletx.request.RouteRequest;
import com.biletx.request.TicketRequest;
import com.biletx.response.BasketResponse;
import com.biletx.response.TotalTicketsAndTotalPricesByVehicleResponse;
import com.biletx.response.VehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VehicleService {
    private static final long MAX_TICKETS_FOR_INDIVIDUAL_USERS = 5;
    private static final long MAX_MALE_PASSENGERS_SINGLE_ORDER_FOR_INDIVIDUAL_USERS = 2;
    private static final long MAX_TICKETS_FOR_CORPORATE_USERS = 20;
    private final Logger logger = Logger.getLogger(Vehicle.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VehicleConverter vehicleConverter;

    @Autowired
    private BasketConverter basketConverter;
    @Autowired
    private BasketRepository basketRepository;

    public VehicleResponse createVehicle(Vehicle vehicle) {

        vehicle.setNo(vehicle.getNo().toUpperCase());
        vehicle.setFromWhere(vehicle.getFromWhere().substring(0, 1).toUpperCase()
                + vehicle.getFromWhere().substring(1).toLowerCase());
        vehicle.setWhereTo(
                vehicle.getWhereTo().substring(0, 1).toUpperCase() + vehicle.getWhereTo().substring(1).toLowerCase());

        switch (vehicle.getVehicleType()) {
            case BUS -> {

                vehicle.setRidership(45);
                vehicle.setEmptySeat(vehicle.getRidership());
                vehicleRepository.save(vehicle);
                logger.log(Level.INFO, "[createVehicle] - VehicleType : " + vehicle.getVehicleType() + " seferi olu??turuldu.");
            }

            case PLANE -> {

                vehicle.setRidership(189);
                vehicle.setEmptySeat(vehicle.getRidership());
                vehicleRepository.save(vehicle);
                logger.log(Level.INFO, "[createVehicle] - VehicleType : " + vehicle.getVehicleType() + " seferi olu??turuldu.");

            }
            default -> {
                logger.log(Level.WARNING, "[createVehicle] - VehicleType : " + vehicle.getVehicleType() + " seferi olu??turulamad??.");
                throw new IllegalStateException("[createVehicle] - Vehicle type : " + vehicle.getVehicleType() + " seferi " +
                        "olu??turulamad??.");
            }
        }
        System.out.println(vehicle.getDepartureTime().toString());
        return vehicleConverter.convert(vehicle);
    }

    public List<VehicleResponse> getAll() {
        List<VehicleResponse> responses = vehicleConverter.convert(vehicleRepository.findAll());
        if (responses.isEmpty()) {
            logger.log(Level.WARNING, "[getAll] - Sefer bulunmamaktad??r.");
            throw new VehicleDoesNotException("[getAll] - Sefer bulunmamaktad??r.");
        }
        logger.log(Level.INFO, "[getAll] - T??m seferler listelendi");
        return responses;
    }
    public List<VehicleResponse> getAllByActive() {
        List<VehicleResponse> responses = vehicleConverter.convert(vehicleRepository.findAll().stream()
                .filter(obj->obj.getStatus().equals(VehicleStatus.ACTIVE))
                .toList());
        if (responses.isEmpty()) {
            logger.log(Level.WARNING, "[getAllByActive] - Aktif sefer bulunmamaktad??r.");
            throw new VehicleDoesNotException("[getAllByActive] - Aktif sefer bulunmamaktad??r.");
        }
        logger.log(Level.INFO, "[getAllByActive] - T??m aktif seferler listelendi");
        return responses;
    }

    public Vehicle getById(int id) {
        return vehicleRepository.findById(id).orElseThrow(() -> {
            logger.log(Level.WARNING, "[getById] - Vehicle id : {0} does not exist.", id);
            throw new VehicleDoesNotException("[getById] - Vehicle id : " + id + "does " + "not exist.");
        });
    }

    @Cacheable(value = "getAll", key = "#id")
    public VehicleResponse cancellationVehicle(Integer id) {

        Vehicle vehicle = getById(id);
        vehicle.setStatus(VehicleStatus.CANCELLED);
        vehicleRepository.save(vehicle);
        logger.log(Level.INFO, "[cancellationVehicle] - id : {0} Sefer iptal edildi.", id);
        return vehicleConverter.convert(vehicle);
    }

    public List<VehicleResponse> getAllByRoute(RouteRequest route) {
        List<VehicleResponse> responses = vehicleConverter.convert(vehicleRepository.findAllByProvince(route.getFromWhere(), route.getWhereTo()));
        if (responses.isEmpty()) {
            logger.log(Level.WARNING, "[getAllByRoute] - Rotas?? : {0} sefer bulunmamaktad??r." + route);
            throw new VehicleDoesNotException("[getAllByRoute] - Rotas?? : " + route + " sefer bulunmamaktad??r.");
        }
        logger.log(Level.INFO, "[getAllByRoute] - Rotas?? : {0} olan t??m seferler listelendi." + route);
        return responses;
    }

    public List<VehicleResponse> getAllByDate(String departureTime) {

        java.sql.Date sql;
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(departureTime);
            sql = new java.sql.Date(parsed.getTime());
            List<VehicleResponse> responses = vehicleConverter.convert(vehicleRepository.findAllByDate(sql));
            System.out.println("---------------------------" + responses.size());
            if (responses.size() == 0) {
                logger.log(Level.WARNING, "[getAllByDate] - Sefer bulunmamaktad??r.");
                throw new VehicleDoesNotException("[getAllByDate] - Sefer bulunmamaktad??r.");
            }
            logger.log(Level.INFO, "[getAllByDate] - {0} tarihine ait t??m seferler listelendi.", departureTime);
            return responses;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "[getAllByDate] - Tarih format?? \"yyyy-MM-dd\" ??eklinde olmal??d??r.");
            throw new VehicleDoesNotException("[getAllByDate] - Tarih format?? \"yyyy-MM-dd\" ??eklinde olmal??d??r.");

        }


    }


    public List<VehicleResponse> getAllByVehicleType(VehicleType vehicleType) {

        List<Vehicle> responses = vehicleRepository.findAll().stream()
                .filter(type -> vehicleType.equals(type.getVehicleType()))
                .toList();
        if (responses.size() == 0) {
            throw new VehicleDoesNotException("[getAllByVehicleType] - " + vehicleType + " t??r??nde sefer bulunmamaktad??r.");
        }
        logger.log(Level.INFO, "[getAllByVehicleType] - {0} t??r??nde t??m seferler listelendi.", vehicleType);
        return vehicleConverter.convert(responses);

    }

    // ara?? kimli??ine g??re sepetteki sipari??lerin say??s??
    public long countSepetByVehicleId(int vehicleId, int userId) {

        long response = basketRepository.findAll().stream()
                .filter(s -> Objects.equals(userId, s.getUserId()))
                .filter(s -> Objects.equals(vehicleId, s.getVehicle().getId())).count();
        logger.log(Level.INFO, "[countSepetByVehicleId] - vehicle_id : {0} ait sepetteki bilet say??s?? {1} ",
                new Integer[]{vehicleId, userId});
        return response;
    }

    // Sepete ekleme
    public List<BasketResponse> sepetAdd(TicketRequest ticket) {
        User foundUser = userService.getById(ticket.getUserId());
        long totalTicket = getTotalTicketByVehicleId(ticket.getVehicleId(), foundUser.getId());
        long emptySeat = getById(ticket.getVehicleId()).getEmptySeat();

        if (getById(ticket.getVehicleId()).getEmptySeat() == 0) {
            logger.log(Level.WARNING, "[sepetAdd] - Urun stokta yok.");
            throw new VehicleDoesNotException("[sepetAdd] - Urun stokta yok.");
        }
        if ((totalTicket == emptySeat)) {
            logger.log(Level.WARNING, "[sepetAdd] - Urun stokta say??s??n?? gecemezsiniz. stok sayisi : {0}", emptySeat);
            throw new VehicleDoesNotException("[sepetAdd] - Urun stokta say??s??n?? gecemezsiniz.");
        }

        // induvidual kullan??c??
        if (userService.isIndividual(foundUser.getId())) {
            if (GenderType.MALE.equals(ticket.getGender())) {
                isMaximumNumberOfMaleOrders(foundUser, MAX_MALE_PASSENGERS_SINGLE_ORDER_FOR_INDIVIDUAL_USERS);
            }
            if (isMaxTicket(totalTicket, MAX_TICKETS_FOR_INDIVIDUAL_USERS, foundUser.getType())) {
                userService.addTicketInBasket(convertBasket(ticket));
                logger.log(Level.INFO, "[sepetAdd] - Urun sepete eklendi. Urun id : " + ticket.getVehicleId());
                return basketConverter.convert(userService.getBasketByUserId(ticket.getUserId()));
            }
        } else {
            // corporator kullan??c??
            if (userService.isCorporate(foundUser.getId())) {
                if (isMaxTicket(totalTicket, MAX_TICKETS_FOR_CORPORATE_USERS, foundUser.getType())) {
                    userService.addTicketInBasket(convertBasket(ticket));
                    logger.log(Level.INFO, "[sepetAdd] - Urun sepete eklendi. Urun id : " + ticket.getVehicleId());
                    return basketConverter.convert(userService.getBasketByUserId(ticket.getUserId()));
                }

            }
        }

        return null;
    }

    // sepet converter
    public Basket convertBasket(TicketRequest request) {
        Basket basket = new Basket();
        basket.setVehicle(getById(request.getVehicleId()));
        basket.setUserId(request.getUserId());
        basket.setGender(request.getGender());
        basket.setPassengerName(request.getPassengerName());
        return basket;
    }


    // Maksimum Erkek Sipari?? Say??s??
    private boolean isMaximumNumberOfMaleOrders(User user, long maxNumberMaleSingleOrderuserstRule) {
        if (numberOfMenInTheBasket(user.getId()) == maxNumberMaleSingleOrderuserstRule) {
            logger.log(Level.WARNING, "[isMaximumNumberOfMaleOrders] - Tek sipariste en fazla " + maxNumberMaleSingleOrderuserstRule + " erkek yolcu icin bilet alabilirsiniz!");
            throw new UserDoesNotException("[isMaximumNumberOfMaleOrders] - Tek sipariste en fazla " + maxNumberMaleSingleOrderuserstRule + " erkek yolcu icin bilet alabilirsiniz!");
        }
        return true;

    }

    // max bilet kontrol??
    private boolean isMaxTicket(long totalTicket, long maxNumberTicketRule, UserType userType) {
        if (totalTicket == maxNumberTicketRule) {
            logger.log(Level.WARNING, "[isMaximumNumberOfMaleOrders] - " + userType + " kullanicilar ayni sefer icin en fazla " + maxNumberTicketRule +
                    " bilet alabilir! = ");
            throw new UserDoesNotException("[isMaximumNumberOfMaleOrders] - " + userType + " kullanicilar ayni sefer icin en fazla maxNumberTicketRule  bilet alabilir! = ");

        }
        return true;
    }

    // ara?? Kimli??ine G??re Toplam Bileti getir
    private long getTotalTicketByVehicleId(int vehicleId, int userId) {
        long countTicketInBasket = countSepetByVehicleId(vehicleId, userId);
        long countBuyingTicket = numberOfTicketsAccordingToVehicleIdAndUserId(vehicleId, userId);
        return countBuyingTicket + countTicketInBasket;
    }

    // ara?? Kimli??i ve Kullan??c?? Kimli??ine G??re Bilet Say??s??
    private long numberOfTicketsAccordingToVehicleIdAndUserId(int vehicleId, int userId) {
        return ticketRepository.findAll().stream().filter(vehicle -> vehicle.getUser().getId().equals(userId))
                .filter(vehicle -> vehicle.getVehicle().getId().equals(vehicleId))
                .count();
    }

    // sepetteki Adam Say??s??
    public long numberOfMenInTheBasket(int userId) {
        return getAllBasketByUserId(userId).stream()
                .filter(basket -> basket.getGender().equals(GenderType.MALE)).count();
    }

    // sepetteki t??m ??r??nleri

    public List<BasketResponse> getAllBasketByUserId(int userId) {
        return basketConverter.convert(userService.getBasketByUserId(userId));
    }

    public TotalTicketsAndTotalPricesByVehicleResponse totalTicketsAndTotalPricesByVehicle(int vehicleId) {
        if (ticketRepository.TotalTicketsByVehicle(vehicleId) == null || ticketRepository.TotalPricesByVehicle(vehicleId) == null) {
            logger.log(Level.WARNING, "vehicleId : {0} Sefere ait sat??n al??nm???? bilet bulunmamaktad??r." + vehicleId);
            throw new TicketDoesNotException("Sefere ait sat??n al??nm???? bilet bulunmamaktad??r.");
        }
        long countTickets = ticketRepository.TotalTicketsByVehicle(vehicleId);
        long countPrices = ticketRepository.TotalPricesByVehicle(vehicleId);

        TotalTicketsAndTotalPricesByVehicleResponse response = new TotalTicketsAndTotalPricesByVehicleResponse();
        response.setTotalTickets(countTickets);
        response.setTolalPrices(countPrices);
        return response;
    }

}
