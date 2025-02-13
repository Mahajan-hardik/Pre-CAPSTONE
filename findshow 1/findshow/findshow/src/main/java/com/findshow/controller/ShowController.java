package com.findshow.controller;

import com.findshow.model.Show;
import com.findshow.model.Screen;
import com.findshow.repository.ShowRepository;
import com.findshow.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ScreenRepository screenRepository;

    // Show all shows
    @GetMapping("/shows")
    public String listShows(Model model) {
        model.addAttribute("shows", showRepository.findAll());
        return "show-list";  // View name
    }

    // Add new show
    @GetMapping("/show/add")
    public String showAddShowForm(Model model) {
        model.addAttribute("show", new Show());
        model.addAttribute("screens", screenRepository.findAll());
        return "show-add";  // View name
    }

    @PostMapping("/show/add")
    public String addShow(@ModelAttribute Show show) {
        showRepository.save(show);
        return "redirect:/admin/shows";  // Redirect to show list after adding
    }

    // Edit show
    @GetMapping("/show/edit/{id}")
    public String showEditShowForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("show", showRepository.findById(id).orElse(null));
        model.addAttribute("screens", screenRepository.findAll());
        return "show-edit";  // View name
    }

    @PostMapping("/show/edit/{id}")
    public String editShow(@PathVariable("id") int id, @ModelAttribute Show show) {
        show.setShowId(id);  // Update the show id before saving
        showRepository.save(show);
        return "redirect:/admin/shows";  // Redirect to show list after editing
    }

    // Delete show
    @GetMapping("/show/delete/{id}")
    public String deleteShow(@PathVariable("id") int id) {
        showRepository.deleteById(id);
        return "redirect:/admin/shows";  // Redirect to show list after deletion
    }
}
