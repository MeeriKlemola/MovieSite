package hh.moviesite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.moviesite.domain.StreamingService;
import hh.moviesite.domain.StreamingServiceRepository;

@Controller
public class StreamingServiceController {

    @Autowired
    private StreamingServiceRepository streamingServiceRepository;

    // http://localhost:8080/streamingservicelist
    @GetMapping(value = "/streamingservicelist")
    public String getStreamingServiceList(Model model) {
        model.addAttribute("streamingservices", streamingServiceRepository.findAll());

        return "streamingservicelist"; // streamingservicelist.html
    }

    // http://localhost:8080/addstreamingservice
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/addstreamingservice")
    public String addStreamingService(Model model) {
        model.addAttribute("streamingservice", new StreamingService());

        return "addstreamingservice"; // addstreamingservice.html
    }

    // Saves form when updating and when saving new streaming service
    @PostMapping("/savestreamingservice")
    public String saveStreamingService(@ModelAttribute StreamingService streamingService) {
        streamingServiceRepository.save(streamingService);

        return "redirect:/streamingservicelist"; // streamingservicelist.html
    }

    // Deletes streaming service with id number
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deletestreamingservice/{id}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") Long streamingserviceId, Model model) {
        streamingServiceRepository.deleteById(streamingserviceId);

        return "redirect:../streamingservicelist"; // streamingservicelist.html
    }

    // Edits streaming service with id number
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editstreamingservice/{id}")
    public String editServiceForm(@PathVariable("id") Long id, Model model) {
        StreamingService streamingService = streamingServiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid streaming service Id:" + id));
        model.addAttribute("streamingservice", streamingService);

        return "editstreamingservice"; // editstreamingservice.html
    }

}
