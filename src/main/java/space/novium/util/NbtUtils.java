package space.novium.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

public class NbtUtils {
    public static int getOrDefault(NbtCompound nbt, String key, int i){
        if(nbt.contains(key, 99)){
            return nbt.getInt(key);
        }
        return i;
    }
    
    public static byte getOrDefault(NbtCompound nbt, String key, byte b){
        if(nbt.contains(key, 99)){
            return nbt.getByte(key);
        }
        return b;
    }
    
    public static boolean getOrDefault(NbtCompound nbt, String key, boolean b){
        if(nbt.contains(key, 99)){
            return nbt.getBoolean(key);
        }
        return b;
    }
    
    public static short getOrDefault(NbtCompound nbt, String key, short s){
        if(nbt.contains(key, 99)){
            return nbt.getShort(key);
        }
        return s;
    }
    
    public static long getOrDefault(NbtCompound nbt, String key, long l){
        if(nbt.contains(key, 99)){
            return nbt.getLong(key);
        }
        return l;
    }
    
    public static float getOrDefault(NbtCompound nbt, String key, float f){
        if(nbt.contains(key, 99)){
            return nbt.getFloat(key);
        }
        return f;
    }
    
    public static double getOrDefault(NbtCompound nbt, String key, double d){
        if(nbt.contains(key, 99)){
            return nbt.getDouble(key);
        }
        return d;
    }
    
    public static String getOrDefault(NbtCompound nbt, String key, String s){
        if(nbt.contains(key, 8)){
            return nbt.getString(key);
        }
        return s;
    }
    
    public static byte[] getOrDefault(NbtCompound nbt, String key, byte[] b){
        if(nbt.contains(key, 7)){
            return nbt.getByteArray(key);
        }
        return b;
    }
    
    public static int[] getOrDefault(NbtCompound nbt, String key, int[] i){
        if(nbt.contains(key, 11)){
            return nbt.getIntArray(key);
        }
        return i;
    }
    
    public static long[] getOrDefault(NbtCompound nbt, String key, long[] l){
        if(nbt.contains(key, 12)){
            return nbt.getLongArray(key);
        }
        return l;
    }
    
    public static NbtElement getOrDefault(NbtCompound nbt, String key, NbtElement n){
        if(nbt.contains(key)){
            return nbt.get(key);
        }
        return n;
    }
    
    public static NbtCompound getOrDefault(NbtCompound nbt, String key, NbtCompound n){
        if(nbt.contains(key)){
            return nbt.getCompound(key);
        }
        return n;
    }
    
    public static boolean containsAll(NbtCompound nbt, String... keys){
        for(String key : keys){
            if(!nbt.contains(key)){
                return false;
            }
        }
        return true;
    }
}
