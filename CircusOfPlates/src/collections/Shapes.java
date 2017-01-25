package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import shapes.Shape;
public class Shapes implements Collection<Shape> {

	private ArrayList<Shape> shapes;
	
	public Shapes (){
		shapes = new ArrayList<Shape>();
	}
	@Override
	public boolean add(Shape shape) {
		
		return shapes.add((Shape) shape);
		
	}

	
	@Override
	public Iterator<Shape> iterator() {
		return new IteratorShapes();
	}

	@Override
	public boolean remove(Object arg0) {
		return shapes.remove((Shape)arg0);
	}
	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	private class IteratorShapes implements Iterator<Shape> {
		
    int pointer =0;
	@Override
	public boolean hasNext() {
		if (pointer< shapes.size()){
			return true;
			
		}
		return false;
	}

	@Override
	public Shape next() {
		if (hasNext()){
			Shape shape =shapes.get(pointer);
			pointer++;
			return shape;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	}
}
