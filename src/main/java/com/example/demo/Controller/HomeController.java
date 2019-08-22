package com.example.demo.Controller;

import com.example.demo.*;
import com.example.demo.Repositories.OrderCombinedRepository;
import com.example.demo.Repositories.OrderDateRepository;
import com.example.demo.Repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class HomeController {

    @Autowired
    OrderCombinedRepository orderCombinedRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDateRepository orderDateRepository;

    @RequestMapping("/")
    public String orderListing(Model model){
        Iterable<OrderDate> dates =  orderDateRepository.findAll();
        Iterable<OrderDetail> details = orderDetailRepository.findAll();
        if(orderCombinedRepository.count() > 0){
            System.out.println("There is data in the combined table");
            orderCombinedRepository.deleteAll();
            joinTables(dates, details);
            model.addAttribute("ordersCombined", orderCombinedRepository.findAll());
        }
        return "index";
    }

    @PostMapping("/filter")
    public String filterListing(@RequestParam(required = false, value = "from-date") String from_date,
                                @RequestParam(required = false, value ="to-date") String to_date,
                                @RequestParam("date-condition") String date_condition,
                                @RequestParam(required = false, value ="from-amount") String from_amount,
                                @RequestParam(required = false, value ="to-amount") String to_amount,
                                @RequestParam("amount-condition") String amount_condition,
                                @RequestParam(required = false, value ="description") String description, Model model){

        Iterable<OrderCombined> orders = null;
        //Filter by date
        if(!from_date.isEmpty() || !to_date.isEmpty()) {
            if (date_condition.equalsIgnoreCase("between")) {
                orders = orderCombinedRepository.findAllByDateBetween(from_date, to_date);
                System.out.println(from_date + to_date);
            } else if (date_condition.equalsIgnoreCase("after")) {
                orders = orderCombinedRepository.findAllByDateAfter(from_date);
                System.out.println(from_date + " ->");
            } else if (date_condition.equalsIgnoreCase("before")) {
                orders = orderCombinedRepository.findAllByDateBefore(from_date);
                System.out.println("<-" + from_date);
            }
        }
        //filter by amount
        if(!from_amount.isEmpty() || !to_amount.isEmpty()) {
            if (amount_condition.equalsIgnoreCase("greater-than-equal")) {
                orders = orderCombinedRepository.findAllByAmountGreaterThanEqual(Float.parseFloat(from_amount));
                System.out.println(from_amount);
            } else if (amount_condition.equalsIgnoreCase("less-than-equal")) {
                orders = orderCombinedRepository.findAllByAmountIsLessThanEqual(Float.parseFloat(from_amount));
                System.out.println(from_amount);
            } else if (amount_condition.equalsIgnoreCase("equal")) {
                orders = orderCombinedRepository.findAllByAmountEquals(Float.parseFloat(from_amount));
                System.out.println(from_amount);
            } else if (amount_condition.equalsIgnoreCase("between")) {
                if(from_amount.isEmpty() || to_amount.isEmpty()){
                    return "redirect:/";
                }else{
                    orders = orderCombinedRepository.findAllByAmountBetween(Float.parseFloat(from_amount), Float.parseFloat(to_amount));
                    System.out.println(from_amount);
                }
            }
        }
        //filter by description
        if(!description.isEmpty()){
            model.addAttribute("ordersCombined", orderCombinedRepository.findAllByDescriptionContains(description));
            return "index";
        }

        model.addAttribute("ordersCombined", orders);
        return "index";
    }


    public void joinTables(Iterable<OrderDate> dates, Iterable<OrderDetail> details){
        ArrayList<OrderCombined> orderCombined = new ArrayList<>();
        String strDate ="", description="";
        boolean moreData = true;
        float amount = 0;
        Iterator<OrderDate> dateIterable = dates.iterator();
        Iterator<OrderDetail> detailIterator = details.iterator();

        while(moreData){
            if(!dateIterable.hasNext() && !detailIterator.hasNext()){
                System.out.println("No more data found");
                break;
            }

            if(dateIterable.hasNext()){
                strDate = dateIterable.next().getDate();
            }

            if(detailIterator.hasNext()) {
                OrderDetail tempOrder = detailIterator.next();
                amount = tempOrder.getAmount();
                description = tempOrder.getDescription();
            }
            orderCombined.add(new OrderCombined(strDate, amount, description));
        }
        orderCombinedRepository.saveAll(orderCombined);
    }

    public Iterable<OrderDate> dateFilter(String from, String to){
        return orderDateRepository.findByDateBetween(from, to);
    }

    public Iterable<OrderDetail> detailAmountFilter(float from, float to){
        return orderDetailRepository.findByAmountBetween(from, to);
    }

    public Iterable<OrderDetail> detailDescriptionFilter(String description){
        return orderDetailRepository.findDistinctByDescription(description);
    }

}
