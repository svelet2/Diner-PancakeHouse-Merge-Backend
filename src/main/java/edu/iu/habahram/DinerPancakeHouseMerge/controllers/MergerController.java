package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import edu.iu.habahram.DinerPancakeHouseMerge.model.DinerMenuIterator;
import edu.iu.habahram.DinerPancakeHouseMerge.model.Iterator;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.DinerRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.PancakeHouseRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/merger")
public class MergerController {

    PancakeHouseRepository pancakeHouseRepository;
    DinerRepository dinerRepository;

    public MergerController(PancakeHouseRepository pancakeHouseRepository, DinerRepository dinerRepository) {
        this.pancakeHouseRepository = pancakeHouseRepository;
        this.dinerRepository = dinerRepository;
    }

    @GetMapping
    public List<MenuItem> get() {
        List<MenuItem> pancakeHouseMenu = pancakeHouseRepository.getTheMenu();
        MenuItem[] dinerMenu = dinerRepository.getTheMenu();

        Iterator dinerIterator = new DinerMenuIterator(dinerMenu);
        List<MenuItem> dinerMenuList = new ArrayList<>();

        while (dinerIterator.hasNext()) {
            dinerMenuList.add((MenuItem) dinerIterator.next());
        }

        Iterator pancakeHouseIterator = new DinerMenuIterator(dinerMenu);
        List<MenuItem> pancakeHouseMenuList = new ArrayList<>();

        while (pancakeHouseIterator.hasNext()) {
            pancakeHouseMenuList.add((MenuItem) pancakeHouseIterator.next());
        }

        return dinerMenuList;
    }
}
