package proyecto_final.backend;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Esta clase es un controlador
@RequestMapping(path="/v1") // Las url's empezaran por "/v1/"
public class MainController {
  @Autowired // Auto-generar el objeto
  private CheckListRepository checkListRepository;
  @Autowired
  private ItemRepository itemRepository;

  @PostMapping(path="/checkList") // Peticiones POST
  public @ResponseBody String addNewCheckList(@RequestParam String name, @RequestParam String description) {

	// Creamos un nuevo CheckList y lo guardamos en su repositorio
    CheckList n = new CheckList();
    n.setName(name);
    n.setDescription(description);
    checkListRepository.save(n);
    return "Saved";
  }

  @DeleteMapping(path="/checkList") // Peticiones DEL
  public @ResponseBody String removeCheckList(@RequestParam Integer id) {

	// Se borra el CheckList seg'un su id
    checkListRepository.deleteById(id);
    return "Deleted";
  }

  @GetMapping(path="/checkList") // Peticiones GET
  public @ResponseBody Iterable<CheckList> getCheckLists(@RequestParam(required=false) Integer[] ids) {
    // Si no se recibi id, se devuelven todas las CheckLists
	if (ids == null) {
		return checkListRepository.findAll();
	}
	// Se se recbe algun id, se devuelven solo esos
	Iterable<Integer> iterable = Arrays.asList(ids);
    return checkListRepository.findAllById(iterable);
  }

  @PostMapping(path="/item") // Peticiones POST
  public @ResponseBody String addNewItem(@RequestParam String name, @RequestParam Integer checkListId) {
	  
	// Comprobamos que el CheckList al que se quiere añadir el Item existe
	Optional<CheckList> optional = checkListRepository.findById(checkListId);
	
	// Si existe el CheckList, se crea el item, y se mete en su repositorio
	if (optional.isPresent()) {
		Item n = new Item();
	    n.setName(name);
	    n.setDone(false);
	    n.setCheckList(optional.get());
	    itemRepository.save(n);
	    return "Saved";
	};
	// Si no existe el CheckList, error
	return "CheckList does not exist";
    
  }

  @PutMapping(path="/item") // Peticiones PUT
  public @ResponseBody String checkItem(@RequestParam Integer id) {
	  
	// Comprobamos que el Item existe
	Optional<Item> optional = itemRepository.findById(id);
	
	// Si el Item existe, cambiamos su estado "done", y lo guardamos
	if (optional.isPresent()) {
		Item item = optional.get();
		item.check();
		itemRepository.save(item);
	    return "Item's new done status is " + item.getDone().toString();
	};
	
	// Si no existe, error
	return "Item does not exist";
    
  }

  @DeleteMapping(path="/item") // Peticiones DEL
  public @ResponseBody String removeItem(@RequestParam Integer id) {

	// Borramos el Item por id
    itemRepository.deleteById(id);
    return "Deleted";
  }
}