package net.fantastisk.deathpoint;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
//import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathPoint extends JavaPlugin implements Listener
{
	//array to store deaths
	List<DeathObject> deathList = new ArrayList<DeathObject>();
	
	
	//detects player has died and sends message
	@EventHandler(priority=EventPriority.NORMAL)
	public void OnDeath(EntityDeathEvent e)
	{ 

		
		if(e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
	        Location playerspot = player.getLocation();
	        
	        
	       String[] playerLocation =  formatdouble(playerspot.getX(),playerspot.getY(),playerspot.getZ());
	       
	       //creating death object
	       DeathObject death = new DeathObject(player.getName(),playerLocation[0],playerLocation[1],playerLocation[2]);
	       
	       
	       // check if deathList is 5 or more remove the oldest
	       if ( deathList.size() >= 5)
	       {
	    	   deathList.remove(0);
	       }
	       deathList.add(death);
	       //sendtoConsole("DeathList has: "+deathList.size()+" Entrys");

	       
	       player.sendMessage(ChatColor.RED+" "+ player.getName()+" Your death location was "+playerLocation[0]+" "+playerLocation[1]+" "+playerLocation[2]);
	       //.sendMessage(player.getName()+" Your death location was "+playerLocation[0]+" "+playerLocation[1]+" "+playerLocation[2]);)
	       sendtoConsole(player.getName()+" died! location was "+playerLocation[0]+" "+playerLocation[1]+" "+playerLocation[2]); 
			//player.sendMessage("You died plugin test"+ playerspot.getX()+" "+playerspot.getY()+" "+playerspot.getZ());
		}

	}

	public boolean onCommand(CommandSender sender, Command command, String label,String[] arguments)
	{
		//how to add commands
		
		command.getName();
		
		if(command.getName().equalsIgnoreCase("died"))
		{
			
			if ( sender instanceof Player)
			{	
				Player player = (Player) sender;
				player.setFoodLevel(20);
				//player.sendMessage(ChatColor.GREEN + "You have been feed!");
				
				//player.sendMessage(Integer.toString(deathList.size()));
				   
				/*
			       for (DeathObject temp : deathList)
			       {
			    	   player.sendMessage(ChatColor.BLUE + temp.getString());
			       }
			    */   
				
				   
			       for(int i = 0; i < deathList.size(); i++)
			       {
			    	   int temp = i + 1;
			    	   player.sendMessage(ChatColor.BLUE+" "+temp+":"+ deathList.get(i).getString());
			       }
			       
				
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "You must be a player to be deded!");

			}
			return true;
		}
		
		return false;
		
	}
	
    @Override
    public void onEnable() 
    {
    	
    	//needed to register listeners for events
    	getServer().getPluginManager().registerEvents(this, this);
    	
    	
    	//logic performed when plugin enabled
    	//getLogger().info("onEnable has been invoked!");
    	//getServer().getLogger().info("FeelMe has been loaded successfully!");
    }

    @Override
    public void onDisable() 
    {
    	
   	
    	// logic performed when plugin disabled 
    	//getLogger().info("onDisable has been invoked!");
    	//getServer().getLogger().info("FeelMe has been unloaded successfully!");
    }
    
    //takes 3 doubles normally coordinate values and shortens to 2 decimal places.
    public String[] formatdouble(double valX,double valZ,double valY)
    {
    	String[] arrayformated = new String[] {String.format("%.2f", valX),String.format("%.2f", valZ),String.format("%.2f", valY)};
    	return arrayformated;
    }
    //send message to server console
    public void sendtoConsole(String message)
    {
    	Bukkit.getConsoleSender().sendMessage(message);
    }
    
    
}
