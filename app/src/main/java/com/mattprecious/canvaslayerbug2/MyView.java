package com.mattprecious.canvaslayerbug2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public final class MyView extends FrameLayout {
  private final Paint filterPaint;
  private final Paint rectPaint;

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setWillNotDraw(false);

    filterPaint = new Paint();
    filterPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_IN));

    rectPaint = new Paint();
    rectPaint.setColor(Color.WHITE);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.GREEN);

    canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), filterPaint, Canvas.ALL_SAVE_FLAG);
    // Draw something to the canvas to work around b/211496
    canvas.drawRect(0, 0, 1, 1, rectPaint);
    canvas.restore();
  }
}
