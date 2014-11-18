package doodledrop;

public class XVec2
{
  public int x, y;

  XVec2()
  {
    x = 0;
    y = 0;
  }

  XVec2(int x_in, int y_in)
  {
    x = x_in;
    y = y_in;
  }

  void set(int x_in, int y_in)
  {
    x = x_in;
    y = y_in;
  }

  public String toString()
  {
    return "(" + x + "," + y + ")";
  }

}
